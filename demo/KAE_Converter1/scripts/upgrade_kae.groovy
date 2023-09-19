import java.util.regex.Pattern

enum LayoutXmlPosition {
    // common
    NONE,
    CLASS_DEFINITION,
    // activity
    SET_CONTENT_VIEW,
    // fragment
    ON_CREATE_VIEW,
    OVERRIDE_METHOD,
}

def pkgName = "ca.six.demo.kae_converter1"

// I. find the target source folder
File projectDir = new File("upgrade_kae.groovy")
projectDir = projectDir.absoluteFile // 不然 file.path就是"kae.groovy", 而不是absolutPath. 这会不利于我们后面找parent
while (projectDir.name != 'KAE_Converter1') {
    projectDir = projectDir.parentFile
}
println projectDir.absolutePath


File appDir = new File(projectDir, "/app/src/main")
File kotlinDir = new File(appDir, "java/ca/six/demo/kae_converter1")
File layoutDir = new File(appDir, "res/layout")


// file.listFiles只列出来了直系child(包括文件与目录), 没有列出孙子文件们
kotlinDir.eachFileRecurse { aFile ->
    if (aFile.isFile()) processEach(aFile, layoutDir, pkgName)
}

// TODO remove debug code                                                       CustomViewPage, StartFragment, EndFragment, /views/SwitchIosView, /views/DetailsView
//def f = new File("/Users/zhengwangsong/code/exp/javakotlin/GroovyWork/assets/kae/app/src/main/java/ca/six/demo/kae_converter1/views/DetailsView.kt")
//processEach(f, layoutDir, pkgName)

// II. process each file
void processEach(File file, File layoutXmlDir, String pkgName) {
    final fileText = file.text
    boolean isKaeFile = fileText.contains("import kotlinx.android.synthetic.main")
    if (!isKaeFile) return

    boolean isFragment = fileText.contains("override fun onViewCreated(")
    boolean isActivity = fileText.contains("override fun onCreate(")
    //严格来说实为: `!isFragment && fileText.contains("override fun onCreate(")`

    if (isFragment) {
        processFragmentFile(file, layoutXmlDir, pkgName)
    } else if (isActivity) {
        processActivityFile(file, layoutXmlDir, pkgName)
    } else {
        processViewFile(file, layoutXmlDir, pkgName)
    }
}

void processActivityFile(File file, File layoutXmlDir, String pkgName) {
    String fileText = file.text
    // 1. remove imports
    String ret = removeKoeImports(fileText)

    // 2. get the layout file name
    def setContentReg = ~"        setContentView\\(R\\.layout\\.(.*)\\)"
    LayoutPositionResult layoutFileResult = getLayoutFileNameFromSetContentView(fileText, setContentReg)
            ?: getLayoutFileNameFromClassDefinition(fileText)
    if (layoutFileResult == null) return
    String bindingName = generateBindingName(layoutFileResult.layoutFileName)
    ret = generateBindingImports(ret, bindingName, pkgName)


    // 3. add definition of binding
    String onCreateDefinition = """    override fun onCreate(savedInstanceState: Bundle?) {"""
    ret = generateBindingDefinition(ret, onCreateDefinition, bindingName, false)


    String setViewRootCode = """        binding = ${bindingName}.inflate(layoutInflater)
        setContentView(binding.root)"""
    if (layoutFileResult.position == LayoutXmlPosition.SET_CONTENT_VIEW) {
        ret = ret.replaceAll(setContentReg, setViewRootCode)
    } else if (layoutFileResult.position == LayoutXmlPosition.CLASS_DEFINITION) {
        String superOnCreate = "super.onCreate(savedInstanceState)"
        String newSuperOnCreate = """$superOnCreate
$setViewRootCode"""
        ret = ret.replace(superOnCreate, newSuperOnCreate)
    }


    // 4. replace views
    def layoutXmlFile = new File(layoutXmlDir, "${layoutFileResult.layoutFileName}.xml")
    ret = replaceOldView(ret, layoutXmlFile, false)

    // 5. write file
    file.write(ret)
}

void processFragmentFile(File file, File layoutXmlDir, String pkgName) {
    LayoutXmlPosition layoutPosition = LayoutXmlPosition.NONE

    String fileText = file.text
    String ret = removeKoeImports(fileText)

    // 2. get the layout file name
    LayoutPositionResult layoutFileResult = getLayoutFileNameFromOverrideMethod(fileText)
            ?: getLayoutFileNameFromOnCreateView(fileText)
            ?: getLayoutFileNameFromClassDefinition(fileText)
    if (layoutFileResult == null) return
    String bindingName = generateBindingName(layoutFileResult.layoutFileName)
    ret = generateBindingImports(ret, bindingName, pkgName)

    // 3. add definition of binding
    def bindingDefinitionAnchor = "    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {"
    ret = generateBindingDefinition(ret, bindingDefinitionAnchor, bindingName, true)

    // 4. remove or modify onCreateView
    ret = removeOrModifyOnCreateView(ret, bindingName)

    // 5. replace view
    def layoutXmlFile = new File(layoutXmlDir, "${layoutFileResult.layoutFileName}.xml")
    ret = replaceOldView(ret, layoutXmlFile, true)

    // 6. write file
    file.write(ret)
}

void processViewFile(File file, File layoutXmlDir, String pkgName) {
    String fileText = file.text
    // 1. remove imports
    String ret = removeKoeImports(fileText)

    // 2. get layout xml file name
    String layoutFileName = getLayoutFileNameFromView(ret)
    String bindingName = generateBindingName(layoutFileName)
    ret = generateBindingImports(ret, bindingName, pkgName)

    // 3. generate definition of binding
    // View可能有两种constructor格式, 没办法, 只好要求replace要覆盖到这两种情况
    String anchor1 = "    init {"
    ret = generateBindingDefinition(ret, anchor1, bindingName, false)
    String anchor2 = "    constructor(context: Context) : this(context, null)"
    ret = generateBindingDefinition(ret, anchor2, bindingName, false)

    // 4. init the new binding variable
    ret = initBindingOfViews(ret, layoutFileName, bindingName)


    // 5. replace usages of view
    def layoutXmlFile = new File(layoutXmlDir, "${layoutFileName}.xml")
    ret = replaceOldView(ret, layoutXmlFile, false)

    // 6. write file
    file.write(ret)
}


// = = = = = = = = = = common methods = = = = = = = = = =
private String removeKoeImports(String fileText) {
    def importReg = ~"import kotlinx\\.android\\.synthetic\\.main\\.(.*)\\.(.*)\n"
    //加一个"\n", 免得replaceAll后还有一个空行占着
    return fileText.replaceAll(importReg, "")
}

private LayoutPositionResult getLayoutFileNameFromClassDefinition(String fileText) {
    def classDefinitionReg = ~"class (.*):(.*)\\(R\\.layout\\.(.*)\\)"
    def classDefinitionMatch = fileText =~ classDefinitionReg
    if (classDefinitionMatch.count > 0) {
        return new LayoutPositionResult(classDefinitionMatch[0][3] as String, LayoutXmlPosition.CLASS_DEFINITION)
    }
    return null
}

private LayoutPositionResult getLayoutFileNameFromSetContentView(String fileText, Pattern reg) {
    def setContentMatch = fileText =~ reg
    if (setContentMatch.count > 0) {
        return new LayoutPositionResult(setContentMatch[0][1] as String, LayoutXmlPosition.SET_CONTENT_VIEW)
    }
    return null
}

/*有一些fragment是BaseFragment中要求子类提供一个id即可, baseFragment自己会在onCreateView中初始化好view. 示例:
class MyFragment: BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.my_account
    }

class AnotherFragment: BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_debug_menu
 */

private LayoutPositionResult getLayoutFileNameFromOverrideMethod(String fileText) {
    def BASEFRAGMENT_LAYOUT_ID_METHOD_NAME = "getLayoutId"

    def reg = ~"(?ms)override fun ${BASEFRAGMENT_LAYOUT_ID_METHOD_NAME}(.*)R\\.layout\\.(.*?)\n"
    // (?ms)表示可以跨多行
    def match = fileText =~ reg
    if (match.count > 0) {
        return new LayoutPositionResult(match[0][2], LayoutXmlPosition.OVERRIDE_METHOD)
    }
    return null
}

private String getLayoutFileNameFromView(String fileText) {
    def reg = ~"LayoutInflater\\.from\\(context\\)\\.inflate\\(R.layout.(.*?),(.*?)\\)"
    def match = fileText =~ reg
    if(match.count < 1) return null
    return match[0][1]
}
/*
class RetailerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_retailer, container, false)
    }

注意, 因为这onCreateView的参数长, 所有的写成一行, 有的写成两行, 有的每个参数一行, 所以这里要小心了
 */

private LayoutPositionResult getLayoutFileNameFromOnCreateView(String fileText) {
    def reg = ~"(?ms)override fun onCreateView(.*?)R\\.layout\\.(.*?),(.*?)\n" // (?ms)表示可以跨多行
    def match = fileText =~ reg
    if (match.count > 0) {
        return new LayoutPositionResult(match[0][2], LayoutXmlPosition.ON_CREATE_VIEW)
    }
    return null
}

class LayoutPositionResult {
    String layoutFileName
    LayoutXmlPosition position

    LayoutPositionResult(String layoutFileName, LayoutXmlPosition position) {
        this.layoutFileName = layoutFileName
        this.position = position
    }

    @Override
    public String toString() {
        return "LayoutPositionResult{layoutFileName=$layoutFileName, position=$position}";
    }
}

private String generateBindingName(String layoutFileName) {
    String bindingName = ""
    layoutFileName.split("_").each {
        bindingName += it.capitalize()
    }
    bindingName += "Binding"
    return bindingName
}

private String generateBindingImports(String text, String bindingName, String pkgName) {
    // import ca.six.demo.viewbinding_end.databinding.ActvCustomViewBinding
    def newLine = System.lineSeparator()
    def index = text.findIndexOf { (it == newLine) }
    def to = "${newLine}import ${pkgName}.databinding.${bindingName}"

    def isInflateExist = text.contains("import android.view.LayoutInflater")
    if(!isInflateExist) {
        to += "${newLine}import android.view.LayoutInflater"
    }

    def isViewGroupExist = text.contains("import android.view.ViewGroup")
    if(!isViewGroupExist) {
        to += "${newLine}import android.view.ViewGroup"
    }

    def sb = new StringBuilder(text).insert(index, to)
    return sb.toString()
}

private String generateBindingDefinition(String text, String anchor, String bindingName, Boolean isNullable) {
    String lateInitCode = """    private lateinit var binding: $bindingName

$anchor"""
    String nullCode = """    private var binding: ${bindingName}? = null

$anchor"""
    String newText = isNullable ? nullCode : lateInitCode
    String result = text.replace(anchor, newText)
    return result
}

private String removeOrModifyOnCreateView(String text, String bindingName) {
    String result = text
    def reg = ~"(?ms)override fun onCreateView(.*?)LayoutInflater(.*?)ViewGroup(.*?)Bundle(.*?)View(.*?)\\{(.*?)\\}\n"
    def match = text =~ reg
    if (match.count > 0) {
        result = result.replaceAll(reg, "")
    }

    def newMethod = """override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ${bindingName}.inflate(inflater)
        return binding?.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }\n"""
    def anchor = "private var binding: ${bindingName}? = null\n"
    result = result.replace(anchor, "$anchor\n    $newMethod")
    return result
}

private String initBindingOfViews(String text, String layoutFileName, String bindingName){
    String from = "LayoutInflater.from(context).inflate(R.layout.${layoutFileName}, this, true)"
    String to = """binding = ${bindingName}.inflate(LayoutInflater.from(context), this, true)"""
    return text.replace(from, to) //最好不用要用replaceAll(regex, to), 毕竟可能有多能有多处inflate
}


private String replaceOldView(String text, File layoutXmlFile, boolean isNullable) {
    String ret = text
    def xmlIdReg = ~"android:id=\"@\\+id/(.*?)\"" // (.*?), 相对于(.*), 表示是non-greedy
    def xmlIdMatch = layoutXmlFile.text =~ xmlIdReg
    for (index in 0..<xmlIdMatch.count) {
        def viewId = xmlIdMatch[index][1]
        def to = isNullable ? "binding!!.${viewId}." : "binding.${viewId}."
        ret = ret.replaceAll("${viewId}\\.", to)
    }
    return ret
}


// ref
// 1). https://stackoverflow.com/questions/1363643/regex-over-multiple-lines-in-groovy

/*
[ReadMe]
1. projectDir, while (projectDir.name != 'KAE_Converter1') , appDir, 这里都要修改成我们目标工程的样子

2. 目标工程要去build.gradle中打开viewbinding
android {
        ...
        viewBinding {
            enabled = true
        }
    }

3.
 */
package model

import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

@CompileStatic
@ToString(includeNames=true)
class SiteMap {
    final Menu menu = new Menu()
    final List<Page> pages = []

    private SiteMap() {}

    public static SiteMap from(File source) {
        CompilerConfiguration config = new CompilerConfiguration()
        def customizer = new ImportCustomizer()
        config.addCompilationCustomizers(customizer)
        customizer.addStaticImport('generator.DocUtils','DOCS_BASEURL')
        config.scriptBaseClass = 'groovy.util.DelegatingScript'
        GroovyShell shell = new GroovyShell(config)
        def script = shell.parse(source)

        def result = new SiteMap()
        ((DelegatingScript)script).setDelegate(result)
        script.run()

        result
    }

    private void pages(Closure pagesSpec) {
        def clone = pagesSpec.rehydrate(this, this, this)
        clone()
    }

    private void menu(Closure menuSpec) {
        def clone = menuSpec.rehydrate(menu, menu, menu)
        clone()
    }

    private void page(String source, String target, Map model = [:]) {
        pages.add(new Page(source:source, target: target, model: model))
    }
}

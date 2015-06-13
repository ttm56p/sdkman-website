
// footer
footer(id: 'footer') {
    div(class: 'row') {
        div(class: 'colset-3-footer') {
            menu.entrySet().eachWithIndex { entry, i ->
                def (name, menu) = [entry.key, entry.value]
                div(class: "col-${i+1}") {
                    h1(name)
                    ul {
                        menu.each { menuItem ->
                            li { a(href: relative(menuItem.link), menuItem.name) }
                        }
                    }
                }
            }
        }
        div(class: 'clearfix', "&copy; 2012-${Calendar.instance[Calendar.YEAR]} SDKman/GVM is Open Source Software licensed under ${$a(href: 'http://www.apache.org/licenses/LICENSE-2.0.html', 'Apache 2')}")
    }
}
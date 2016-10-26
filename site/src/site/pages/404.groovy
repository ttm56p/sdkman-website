/**
 * Ths template is generating the 404 page
 */

layout 'layouts/main.groovy', true,
        pageTitle: 'SDKMAN!, the Software Development Kit Manager- 404',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'panel panel-danger') {
                            div(class: 'panel-heading', 'Oops! The page you are looking for does not exist!')
                            div(class: 'panel-body') {
                                p 'We could not find the page you are looking for. Maybe you are looking for one of these?'
                                ul {
                                    li("The ${$a(href: "/install.html", 'Installation Page')}.")
                                    li("The ${$a(href: "/usage.html", 'Usage Page')}.")
                                    li("The ${$a(href: "/vendors.html", 'Vendors Page')}.")
                                }
                            }
                        }
                    }
                }
            }
        }


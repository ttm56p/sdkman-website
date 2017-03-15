layout 'layouts/main.groovy', true,
        pageTitle: 'SDKs that SDKMAN! can install.',
        mainContent: contents {
            div(id: 'content') {
                div(class: 'row') {
                    div(class: 'col-lg-10') {
                        h3('SDKs that SDKMAN! can install:')
                        //pre(id: 'unique') {
                            include unescaped: 'html/candidates.html'
                        //}
                    }
                }
            }
        }

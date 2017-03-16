layout 'layouts/main.groovy', true,
        pageTitle: 'Available SDKs - SDKMAN!',
        mainContent: contents {
            div(id: 'content') {
                div(class: 'row') {
                    div(class: 'col-lg-10') {
                        h3('SDK Candidates available for installation on SDKMAN!')
                        include unescaped: 'html/candidates.html'
                    }
                }
            }
        }

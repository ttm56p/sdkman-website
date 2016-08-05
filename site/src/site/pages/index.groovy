layout 'layouts/main.groovy', true,
        pageTitle: 'SDKMAN! the Software Development Kit Manager',
        mainContent: contents {
            div(id: 'band', class: 'band') {

            }
            div(id: 'content') {
                include unescaped: 'html/index.html'
            }
        }

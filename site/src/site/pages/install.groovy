layout 'layouts/main.groovy', true,
        pageTitle: 'Install SDKman',
        mainContent: contents {
            div(id: 'band', class: 'band') {

            }
            div(id: 'content') {
                include unescaped: 'html/install.html'
            }
        }

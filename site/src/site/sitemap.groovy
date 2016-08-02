menu {
    group('sdkman') {
        item 'Install',                     'install.html'
        item 'Usage',                       'usage.html'
//        item 'API',                         'api.html'
        item 'Vendors',                     'vendors.html'
    }

    group('Socialize') {
        item 'Discuss on Gitter',           'https://gitter.im/sdkman/user-issues',             'fa-envelope'
        item 'sdkman on Twitter',           'https://twitter.com/sdkmanager',                   'fa-twitter'
        item 'Source code on GitHub',       'https://github.com/sdkman',                        'fa-github'
        item 'Report issues on GitHub',     'https://github.com/sdkman/sdkman-cli/issues',      'fa-bug'
        item 'Stack Overflow questions',    'http://stackoverflow.com/questions/tagged/sdkman', 'fa-stack-overflow'
    }
}

pages {
    page 'index', 'index'
    page 'install', 'install'
    page 'usage', 'usage'
//    page 'api', 'api'
    page 'vendors', 'vendors'
    page '404','404', [:]
}

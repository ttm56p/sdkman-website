menu {
    group('SDKman') {
        item 'Install',                     'install.html'
        item 'Usage',                       'usage.html'
        item 'API',                         'api.html'
        item 'Vendors',                     'vendors.html'
    }

    group('Socialize') {
        item 'Discuss on the Nable forum',  'http://forum.gvmtool.net',                         'fa-envelope'
        item 'SDKman on Twitter',           'https://twitter.com/sdkmanager',                   'fa-twitter'
        item 'Source code on GitHub',       'https://github.com/sdkman',                        'fa-github'
        item 'Report issues on GitHub',     'https://github.com/sdkman/sdkman-cli/issues',      'fa-bug'
        item 'Google+ Groovy Page',         'plus.google.com/110870000286657737858',            'fa-google-plus'
        item 'Stack Overflow questions',    'http://stackoverflow.com/questions/tagged/gvm',    'fa-stack-overflow'
    }
}

pages {
    page 'index', 'index'
    page 'install', 'install'
    page 'usage', 'usage'
    page 'api', 'api'
    page 'vendors', 'vendors'
    page '404','404', [:]
}
article {
    a(name: 'version'){}
    h2 { yield 'sdkman Version' }
    p {
        yield 'Display the current version of sdkman:'
        pre { code '$ sdk version' }
    }
}
br()

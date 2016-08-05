article {
    a(name: 'version'){}
    h2 { yield 'SDKMAN! Version' }
    p {
        yield 'Display the current version of SDKMAN!:'
        pre { code '$ sdk version' }
    }
}
br()

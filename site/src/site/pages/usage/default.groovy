article {
    a(name: 'default'){}
    h3 { yield 'Default Version' }
    p {
        yield 'Chose to make a given version the default:'
        pre { code '$ sdk default scala 2.11.6' }
        yield 'This will ensure that all subsequent shells will start with version 2.11.6 in use.'
    }
}

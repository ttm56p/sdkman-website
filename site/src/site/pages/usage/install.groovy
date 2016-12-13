article {
    h2 { yield 'Installing an SDK'}
    a(name: 'installdefault'){}
    h4 { yield 'Latest Stable' }
    p {
        yield 'Install the '
        strong 'latest stable'
        yield ' version of your SDK of choice (say, Java JDK) by running the following command:'
        pre { code '$ sdk install java' }
        yield 'You will see something like the following output:'
        pre {
            code '''
Downloading: java 8u111

In progress...

######################################################################## 100.0%

Installing: java 8u111
Done installing!
'''
        }
        yield 'Now you will be prompted if you want this version to be set as '
        strong 'default.'
        pre { code 'Do you want java 8u111 to be set as default? (Y/n):' }
        yield 'Answering '
        em 'yes'
        yield ' (or '
        em 'hitting enter'
        yield ') will ensure that all subsequent shells opened will have this version of the SDK in use by default.'
        pre { code 'Setting java 8u111 as default.' }
    }

    a(name: 'installspecific'){}
    h4 { yield 'Specific Version' }
    p {
        yield 'Need a '
        strong 'specific'
        yield ' version of an SDK? Simply qualify the version you require:'
        pre { code '$ sdk install scala 2.12.1'  }
        yield 'All subsequent steps same as above.'
    }
    a(name: 'localversion'){}
    h4 { yield 'Install Local Version(s)' }
    p {
        yield 'Need a snapshot? Already have a local installation? Setup a local version:'
        pre { code '$ sdk install groovy 3.0.0-SNAPSHOT /path/to/groovy-3.0.0-SNAPSHOT' }
    }
}
br()

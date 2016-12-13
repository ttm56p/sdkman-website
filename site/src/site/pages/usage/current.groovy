article {
    a(name: 'current'){}
    h2 { yield 'Current Version(s)' }
    p {
        yield 'To see what is currently in use for a Candidate:'
        pre { code '''
$ sdk current java
  Using java version 8u111
'''     }
        yield 'To see what is currently in use for '
        strong 'all'
        yield ' Candidates:'
        pre { code '''
$ sdk current
  Using:
  groovy: 2.4.7
  java: 8u111
  scala: 2.12.1
'''     }

    }
}
br()

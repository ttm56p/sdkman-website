article {
    a(name: 'current'){}
    h2 { yield 'Current Version(s)' }
    p {
        yield 'To see what is currently in use for a Candidate:'
        pre { code '''
$ sdk current grails
  Using grails version 2.4.3
'''     }
        yield 'To see what is currently in use for '
        strong 'all'
        yield ' Candidates:'
        pre { code '''
$ sdk current
  Using:
  groovy: 2.1.0
  scala: 2.11.7
'''     }

    }
}
br()

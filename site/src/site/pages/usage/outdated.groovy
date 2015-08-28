article {
    a(name: 'outdated'){}
    h3 { yield 'Outdated Version(s)' }
    p {
        yield 'To see what is currently out of date for a Candidate on your system:'
        pre { code '''
$ sdk outdated springboot
  Outdated:
  springboot (1.2.4.RELEASE, 1.2.3.RELEASE < 1.2.5.RELEASE)
'''     }
        yield 'To see what is outdated for '
        strong 'all'
        yield ' Candidates:'
        pre { code '''
$ sdk outdated
  Outdated:
  gradle (2.3, 1.11, 2.4, 2.5 < 2.6)
  grails (2.5.1 < 3.0.4)
  springboot (1.2.4.RELEASE, 1.2.3.RELEASE < 1.2.5.RELEASE)
'''     }
    }
}

article {
    a(name: 'broadcast'){}
    h2 { yield 'Broadcast Messages' }
    p {
        yield 'Get the latest SDK release notifications on the command line:'
        pre { code '''
$ sdk broadcast
==== BROADCAST =================================================================
* 06/12/16: Scala 2.12.1 released on SDKMAN! #scala
* 23/11/16: Gradle 3.2.1 released on SDKMAN! #gradle
* 22/11/16: Ceylon 1.3.1 released on SDKMAN! #ceylonlang
================================================================================
'''     }
        yield '''It is also worth mentioning that whenever an SDK version is released on
                 SDKMAN!, a notification will appear when next using the CLI.
                 Every new broadcast is also pushed to Twitter.'''
    }
}
br()

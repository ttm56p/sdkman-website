article {
    a(name: 'broadcast'){}
    h2 { yield 'Broadcast Messages' }
    p {
        yield 'Get the latest SDK release notifications on the command line:'
        pre { code '''
$ sdk broadcast
  ==== BROADCAST =================================================================
  * 25/08/15: Jbossforge 2.18.0.Final has been released on SDKMAN! #JBossForge
  * 22/08/15: Springboot 1.3.0.M4 has been released on SDKMAN! #springboot
  * 12/08/15: Springboot 1.3.0.M3 has been released on SDKMAN! #springboot
  ================================================================================
'''     }
        yield '''It is also worth mentioning that whenever an SDK version is released on
                                             SDKMAN!, a notification will appear when next using the CLI.
                                             Every new broadcast is also pushed to Twitter.'''
    }
}
br()

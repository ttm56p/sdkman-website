article {
    a(name: 'offline'){}
    h2 { yield 'Offline Mode' }
    p {
        yield 'Initially called '
        em 'Aeroplane Mode'
        yield ', this allows sdkman to function when working offline. '
        yield 'It has a parameter that can be passed to'
        em 'enable'
        yield ' or '
        em 'disable'
        yield ' the offline mode.'
        pre { code '''
$ sdk offline enable
  Forced offline mode enabled.

$ sdk offline disable
  Online mode re-enabled!
'''     }
        yield 'When operating in '
        strong 'offline'
        yield ' mode, most commands will still work even though they will operate in '
        yield 'a scaled down capacity. An example is the list command, which will only '
        yield 'display the currently installed and active version(s):'
        pre { code '''
$ sdk offline enable
  ------------------------------------------------------------
  Offline Mode: only showing installed groovy versions
  ------------------------------------------------------------
   > 2.4.4
   * 2.4.3
  ------------------------------------------------------------
  * - installed
  > - currently in use
  ------------------------------------------------------------
'''     }
        yield 'The offline mode will also be disabled/enabled automatically when the internet '
        yield 'becomes available/unavailable. Naturally, commands that require internet connectivity '
        yield 'will not function but give a warning.'
    }
}
br()
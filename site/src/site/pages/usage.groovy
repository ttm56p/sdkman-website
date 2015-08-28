layout 'layouts/main.groovy', true,
        pageTitle: 'Usage - sdkman',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            include template: 'includes/usage-navbar.groovy'
                            br()
                            include unescaped: 'html/twittersearch.html'
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            h1 {
                                a(name: 'main'){}
                                i(class: 'fa fa-laptop'){}
                                yield ' Usage'
                            }

                            article {
                                h3 { yield 'Installing an SDK'}
                                a(name: 'installdefault'){}
                                h4 { yield 'Latest Stable' }
                                p {
                                    yield 'Install the '
                                    strong 'latest stable'
                                    yield ' version of your SDK of choice (say, Gradle) by running the following command:'
                                    pre { code '$ sdk install gradle' }
                                    yield 'You will see the following output:'
                                    pre {
                                        code '''
Downloading: gradle 2.6

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100 39.0M  100 39.0M    0     0   945k      0  0:00:42  0:00:42 --:--:--  978k

Installing: gradle 2.6
Done installing!
'''
                                    }
                                    yield 'Now you will be prompted if you want this version to be set as '
                                    strong 'default.'
                                    pre { code 'Do you want gradle 2.5 to be set as default? (Y/n):' }
                                    yield 'Answering '
                                    em 'yes'
                                    yield ' (or '
                                    em 'hitting enter'
                                    yield ') will ensure that all subsequent shells opened will have this version of the SDK in use by default.'
                                    pre { code 'Setting gradle 2.5 as default.' }
                                }

                                a(name: 'installspecific'){}
                                h4 { yield 'Specific Version' }
                                p {
                                    yield 'Need a '
                                    strong 'specific'
                                    yield ' version of an SDK? Simply qualify the version you require:'
                                    pre { code '$ sdk install scala 2.11.7'  }
                                    yield 'All subsequent steps same as above.'
                                }
                            }
                            article {
                                a(name: 'remove'){}
                                h3 { yield 'Remove Version' }
                                p {
                                    yield 'Remove an installed version.'
                                    pre { code '$ sdk remove scala 2.11.6' }
                                }
                            }
                            article {
                                a(name: 'list'){}
                                h3 { yield 'List Version' }
                                p {
                                    yield 'To get a listing of what is available, installed and selected:'
                                    pre { code '$ sdk list groovy' }
                                    yield 'This will result in a list view showing the available, local, installed and current versions of the SDK.'
                                    pre { code '''
================================================================================
Available Groovy Versions
================================================================================
 > * 2.4.4                2.3.1                2.0.8                1.8.3          
     2.4.3                2.3.0                2.0.7                1.8.2          
     2.4.2                2.2.2                2.0.6                1.8.1          
     2.4.1                2.2.1                2.0.5                1.8.0          
     2.4.0                2.2.0                2.0.4                1.7.9          
     2.3.9                2.1.9                2.0.3                1.7.8          
     2.3.8                2.1.8                2.0.2                1.7.7          
     2.3.7                2.1.7                2.0.1                1.7.6          
     2.3.6                2.1.6                2.0.0                1.7.5          
     2.3.5                2.1.5                1.8.9                1.7.4          
     2.3.4                2.1.4                1.8.8                1.7.3          
     2.3.3                2.1.3                1.8.7                1.7.2          
     2.3.2                2.1.2                1.8.6                1.7.11         
     2.3.11               2.1.1                1.8.5                1.7.10         
     2.3.10               2.1.0                1.8.4                1.7.1          

================================================================================
+ - local version
* - installed
> - currently in use
================================================================================

                                    '''
                                    }
                                }
                            }
                            article {
                                a(name: 'use'){}
                                h3 { yield 'Use Version' }
                                p {
                                    yield 'Choose to use a given version in the current terminal:'
                                    pre { code '$ sdk use scala 2.11.6' }
                                    yield 'It is important to realise that this will switch the candidate version '
                                    strong 'for the current shell only'
                                    yield '. To make this change permanent, use the '
                                    a(href:'#default', 'default')
                                    yield ' command instead.'
                                }
                            }
                            article {
                                a(name: 'default'){}
                                h3 { yield 'Default Version' }
                                p {
                                    yield 'Chose to make a given version the default:'
                                    pre { code '$ sdk default scala 2.11.6' }
                                    yield 'This will ensure that all subsequent shells will start with version 2.11.6 in use.'
                                }
                            }
                            article {
                                a(name: 'current'){}
                                h3 { yield 'Current Version(s)' }
                                p {
                                    yield 'To see what is currently in use for a Candidate:'
                                    pre { code '''
$ sdk current grails
  Using grails version 2.4.3'''     }
                                    yield 'To see what is currently in use for '
                                    strong 'all'
                                    yield ' Candidates:'
                                    pre { code '''
$ sdk current
  Using:
  groovy: 2.1.0
  scala: 2.11.7''' }

                                }
                            }
                            article {
                                a(name: 'outdated'){}
                                h3 { yield 'Outdated Version(s)' }
                                p {
                                    yield 'To see what is currently out of date for a Candidate on your system:'
                                    pre { code '''
$ sdk outdated springboot
  Outdated:
  springboot (1.2.4.RELEASE, 1.2.3.RELEASE < 1.2.5.RELEASE)''' }
                                    yield 'To see what is outdated for '
                                    strong 'all'
                                    yield ' Candidates:'
                                    pre { code '''$ sdk outdated
  Outdated:
  gradle (2.3, 1.11, 2.4, 2.5 < 2.6)
  grails (2.5.1 < 3.0.4)
  springboot (1.2.4.RELEASE, 1.2.3.RELEASE < 1.2.5.RELEASE)''' }
                                }
                            }
                            article {
                                a(name: 'version'){}
                                h3 { yield 'sdkman Version' }
                                p {
                                    yield 'Display the current version of sdkman:'
                                    pre { code '$ sdk version' }
                                }
                            }
                            article {
                                a(name: 'broadcast'){}
                                h3 { yield 'Broadcast Messages' }
                                p {
                                    yield 'Get the latest SDK release notifications on the command line:'
                                    pre { code '''
$ sdk broadcast
  ==== BROADCAST =================================================================
  * 25/08/15: Jbossforge 2.18.0.Final has been released on GVM. #JBossForge
  * 22/08/15: Springboot 1.3.0.M4 has been released on GVM. #springboot
  * 12/08/15: Springboot 1.3.0.M3 has been released on GVM. #springboot
  ================================================================================''' }
                                    yield '''It is also worth mentioning that whenever an SDK version is released on
                                             sdkman, a notification will appear when next using the CLI.
                                             Every new broadcast is also pushed to Twitter.'''
                                }
                            }

                            article {
                                a(name: 'offline'){}
                                h3 { yield 'Offline Mode' }
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
''' }
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
''' }
                                    yield 'The offline mode will also be disabled/enabled automatically when the internet '
                                    yield 'becomes available/unavailable. Naturally, commands that require internet connectivity '
                                    yield 'will not function but give a warning.'
                                }
                            }

                            article {
                                a(name: 'selfupdate'){}
                                h3 { yield 'Self-Update' }
                                p {
                                    yield 'Installs a new version of sdkman if available.'
                                    pre { code '$ sdk selfupdate' }
                                    yield 'If no new version is available an appropriate message will be displayed. '
                                    yield 'Re-installation may be forced by passing the force parameter to the command:'
                                    pre { code '$ sdk selfupdate force' }
                                    yield 'Automatic daily checks for new versions of sdkman will also be performed on the behalf of the user.'
                                }
                            }

                            article {
                                a(name: 'flush'){}
                                h3 { yield 'Flush' }
                                p {
                                    yield """From time to time it may be necessary to flush sdkman's local state.
                                             The flush command helps with this and allows for the following to be performed:"""

                                    h4 { yield 'Candidates' }
                                    pre { code '$ sdk flush candidates' }
                                    yield 'Clears out the Candidate list. Opening a new terminal will fetch and store the latest list.'
                                    yield 'This is usually required when a new Candidate is made available on sdkman.'

                                    h4 { yield 'Broadcast' }
                                    pre { code '$ sdk flush broadcast' }
                                    yield 'Clears out the Broadcast cache, downloading the latest available news on next command invocation.'

                                    h4 { yield 'Archives' }
                                    pre { code '$ sdk flush archives' }
                                    yield 'Cleans the cache containing all downloaded SDK binaries. This can take up a lot of space so is worth clearing out from time to time!'

                                    h4 { yield 'Archives' }
                                    pre { code '$ sdk flush archives' }
                                    yield 'Cleans the cache containing all downloaded sdk binaries. This can take up a lot of space so is worth clearing out from time to time!'

                                    h4 { yield 'Temporary Folder' }
                                    pre { code '$ sdk flush temp' }
                                    yield 'Clears out the staging work folder used when installing new versions of candidates and sdkman itself.'


                                }
                            }
                            article {
                                a(name: 'help'){}
                                h2 { yield 'Help' }
                                p {
                                    yield 'You can get basic help by running the following command:'
                                    pre { code '$ sdk help' }
                                    yield 'This should yield something like:'
                                    pre {
                                        code '''
Usage: sdk <command> <candidate> [version]
       sdk offline <enable|disable>

   commands:
       install   or i    <candidate> [version]
       uninstall or rm   <candidate> <version>
       list      or ls   <candidate>
       use       or u    <candidate> [version]
       default   or d    <candidate> [version]
       current   or c    [candidate]
       outdated  or o    [candidate]
       version   or v
       broadcast or b
       help      or h
       offline           <enable|disable>
       selfupdate        [force]
       flush             <candidates|broadcast|archives|temp>

   candidate  :  ...
   version    :  where optional, defaults to latest stable if not provided

eg: sdk install groovy
'''
                                    }
                                }

                                hr(class: 'divider')
                            }
                        }
                    }
                }
            }
        }
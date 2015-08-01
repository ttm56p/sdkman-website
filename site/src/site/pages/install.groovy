layout 'layouts/main.groovy', true,
        pageTitle: 'Installation - SDKman',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            include unescaped: 'html/twittersearch.html'
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            h1 {
                                i(class: 'fa fa-cloud-download') {}
                                yield ' Installation'
                            }
                            article {
                                p {
                                    yield '''Installing Software Development Kits (SDKs) on any UNIX-like platform
                                             has never been easier. SDKman installs smoothly on Mac OSX, Linux, Cygwin,
                                             Solaris and FreeBSD. We also support both Bash and ZSH.'''
                                    br()
                                    yield 'Simply open a new terminal and enter: '
                                }
                                pre { code '$ curl -s get.sdkman.io | bash' }
                                p {
                                    yield 'Follow the instructions on-screen to complete installation.'
                                    br()
                                    yield 'Next, open a new terminal or type the command:'
                                }
                                pre { code '$ source "$HOME/.sdkman/bin/sdkman-init.sh"' }
                                p 'Lastly, run the following code snippet to ensure that installation succeeded:'
                                pre { code '$ sdk help' }
                                p 'If all went well, you should see the something resembling:'
                                pre { code '''
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

   candidate  :  ....
   version    :  where optional, defaults to latest stable if not provided

eg: sdk install groovy''' }

                                p 'That\'s all there is to it!'
                            }
                            hr(class: 'divider')
                        }
                    }
                }
            }
        }
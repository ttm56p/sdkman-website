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
                                    yield '''Installing SDKman on UNIX-like platforms is as easy as ever.
                                             SDKman installs smoothly on Mac OSX, Linux, Cygwin, 
                                             Solaris and FreeBSD. We also support Bash and ZSH shells.'''
                                    br()
                                    yield 'Simply open a new terminal and enter: '
                                }
                                pre { code '$ curl -s get.sdkman.io | bash' }
                                p {
                                    yield 'Follow the instructions on-screen to complete installation.'
                                    br()
                                    yield 'Next, open a new terminal '
                                    b 'or'
                                    yield ' enter:'
                                }
                                pre { code '$ source "$HOME/.sdkman/bin/sdkman-init.sh"' }
                                p 'Lastly, run the following code snippet to ensure that installation succeeded:'
                                pre { code '$ sdk version' }
                                p 'If all went well, you should see the version information:'
                                pre { code 'SDKman 2.4.2' }
                                p {
                                    yield 'That\'s all there is to it! Next we will look at '
                                    a(href: "/usage.html", 'Usage')
                                    yield '.'
                                }
                            }
                        }
                    }
                }
            }
        }

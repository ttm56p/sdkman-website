layout 'layouts/main.groovy', true,
        pageTitle: 'Installation - sdkman',
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
                                    yield '''Installing sdkman on UNIX-like platforms is as easy as ever.
                                             sdkman installs smoothly on Mac OSX, Linux, Cygwin,
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
                                pre { code '  sdkman 2.4.3' }
                                p {
                                    yield 'That\'s all there is to it! Next we will look at '
                                    a(href: "/usage.html", 'Usage')
                                    yield '.'
                                }
                            }

                            article {
                                h3 {
                                    yield 'Installing to a Custom Location'
                                }
                                p {
                                    yield 'It is possible to install sdkman to a custom location other than '
                                    code '$HOME/.sdkman'
                                    yield '. This can be achieved by exporting your custom location as '
                                    code 'SDKMAN_DIR'
                                    yield ' prior to installing.'
                                    br()
                                    yield 'Simply open a new terminal and enter:'
                                    pre { code '$ export SDKMAN_DIR="/usr/local/sdkman" && curl -s get.sdkman.io | bash' }
                                    yield 'For this to work it is vital that your user has full access rights to this folder.'
                                    yield 'It is also important that the folder does not exist as sdkman will attempt to create it.'
                                }
                            }
                        }
                    }
                }
            }
        }

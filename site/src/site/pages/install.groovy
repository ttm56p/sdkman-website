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
                                pre { code '$ curl -s get.gvmtool.net | bash' }
                                p {
                                    yield 'Follow the instructions on-screen to complete installation.'
                                    br()
                                    yield 'Next, open a new terminal or type the command:'
                                }
                                pre { code '$ source "$HOME/.gvm/bin/gvm-init.sh"' }
                                p 'That\'s all there is to it!'
                            }
                            hr(class: 'divider')
                        }
                    }
                }
            }
        }
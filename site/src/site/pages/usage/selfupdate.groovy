article {
    a(name: 'selfupdate'){}
    h2 { yield 'Self-Update' }
    p {
        yield 'Installs a new version of sdkman if available.'
        pre { code '$ sdk selfupdate' }
        yield 'If no new version is available an appropriate message will be displayed. '
        yield 'Re-installation may be forced by passing the force parameter to the command:'
        pre { code '$ sdk selfupdate force' }
        yield 'Automatic daily checks for new versions of sdkman will also be performed on the behalf of the user.'
    }
}
br()
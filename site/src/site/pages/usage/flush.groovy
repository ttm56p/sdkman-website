article {
    a(name: 'flush'){}
    h2 { yield 'Flush' }
    p {
        yield "From time to time it may be necessary to flush sdkman's local state."
        yield "The flush command helps with this and allows for the following to be performed:"

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

        h4 { yield 'Temporary Folder' }
        pre { code '$ sdk flush temp' }
        yield 'Clears out the staging work folder used when installing new versions of candidates and sdkman itself.'

    }
}
br()

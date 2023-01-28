job('hellow_world_job') {
    steps {
        shell('python scripts/hello.py')
    }
}
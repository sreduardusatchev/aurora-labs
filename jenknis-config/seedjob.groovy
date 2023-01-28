job('seed_job') {
  scm {
    git {
      remote {
        url('https://github.com/sreduardusatchev/aurora-labs.git')
      }
      branch('main')
    }
  }
  steps {
    dsl {
      external('hellow_world_job.groovy')
    }
  }
}
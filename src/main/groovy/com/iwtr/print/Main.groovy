/**
 * Copyright 2012 Alessandro Novarini
 *
 * This file is part of the iwtr project.
 *
 * Iwtr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.iwtr.print

def cli = new CliBuilder(usage: 'iwtr-print <module name>')

def options = cli.parse(args)


if (options.arguments()) {
    printGraph(options)
}
else {
    cli.usage()
}

private void printGraph(OptionAccessor options) {
    File repositoryLocation = defaultRepositoryLocation()
    def repository = new Repository(repositoryLocation)

    try {
        printGraph(repository, options)
    }
    finally {
        repository.close()
    }
}

private void printGraph(Repository repository, OptionAccessor options) {
    repository.init()

    def moduleName = options.arguments()[0]
    def modulesGroup = repository.modulesGroupedByDistanceFrom(moduleName)

    modulesGroup.each {
        modulesGroup[it.key] = it.value.collect {it.key}
    }

    modulesGroup.each {
        println "Group $it.key"
        it.value.each {
            println it
        }
        println "-" * 30
    }
}

private File defaultRepositoryLocation() {
    String homeDir = System.getProperty("user.home")
    def defaultRepositoryLocation = new File("$homeDir/.iwtr")
    if (!defaultRepositoryLocation.exists()) {
        System.err.println "No repository found at $defaultRepositoryLocation"
        System.err.println "Did you run iwtr-import first?"
        System.exit(1);
    }
    defaultRepositoryLocation
}

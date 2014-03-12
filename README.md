The purpose of this codebase is to:
* Generate a json file for each hierarchy from the D3 database
* Upload a jar containing the json file and java classes to parse and use the data to the internal artifactory

The user of this codebase will:
* Include jar as a dependency
* Get hierarchy info from jar (i.e. if a new department or style etc is added, it will be in the library)
* Be able to upgrade to use new departments/divisions at the leisure of each project
* Using this library is faster and requires less setup than connecting to a db.

This app is to generate libraries, NOT to ever be deployed to a production environment. 

Note: This is not an official project. This is based on my personal opinions about a pain point that my team has been feeling.

TODO/Note: currently, some of the "unit" tests are not actually unit tests. They talk to the database and the filesystem. This is a hacky project and never intended to be prod code.
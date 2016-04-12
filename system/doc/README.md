# Instructions on how to generate API docs

Prerequisite :

We use the apidoc tool (refer to www.apidocjs.com) to generate nice docs for our backend RESTful APIs. It requires npm/nodejs to start and download the apidoc plugin. Go to the apidocjs website for more information on how to install and configure the apidoc tool.

Instructions :

* File apidoc.json is the apidocjs configure file. If this file is not included in the folder, then create a file named as apidoc.json.
Then add configuration information in this file.

Example:
{
  "name": "Mode",
  "version": "1.0.0",
  "description": "This is the document for the mode restful apis",
  "title": "Mode Restful Api"
}

* Run the apidoc tool from command line, as follows:

apidoc -i ${mode-platform}/src/main/java/com/mode/controller/ -o apidoc/

* Go to the ./apidoc directory and check the latest docs. 

Enjoy!

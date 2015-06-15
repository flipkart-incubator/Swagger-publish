<#if swagger.getBasePath()??> <p> <b> Basepath : </b> ${swagger.getBasePath()} </p> </#if>

<#if swagger.getPaths()??>
    <#assign paths = swagger.getPaths()/>
    <#list paths?keys as path>
    <h2> ${path}</h2>
        <#assign operation = paths?values[path_index]>

        <#if operation.getGet()??>
            <#assign get = operation.getGet()/>
        <table border = "1">
            <tr>
                <td> Method </td>
                <td> GET </td>
            </tr>
            <#if get.getSummary()??>
                <tr>
                    <td>Summary</td>
                    <td>${get.getSummary()}</td>
                </tr>
            </#if>
            <#if get.getDescription()??>
                <tr>
                    <td>Description</td>
                    <td>${get.getDescription()}</td>
                </tr>
            </#if>
            <#if get.getOperationId()??>
                <tr>
                    <td>Operation Id</td>
                    <td>${get.getOperationId()}</td>
                </tr>
            </#if>
            <#if get.getProduces()??>
                <tr>
                    <td> Produces </td>
                    <td>
                        <table border = "1">
                            <#list get.getProduces() as produces>
                                <td> ${produces} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if get.getConsumes()??>
                <tr>
                    <td> Consumes </td>
                    <td>
                        <table border = "1">
                            <#list get.getConsumes() as consumes>
                                <td> ${consumes} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if get.getParameters()??>
                <tr>
                    <td> Parameters </td>
                    <td>
                        <table border = "1">
                            <tr>
                                <td> Name </td>
                                <td> Param Type </td>
                                <td> Description </td>
                                <td> Required </td>
                                <td> Type </td>
                            </tr>
                            <#list get.getParameters() as Parameter>
                                <tr>
                                    <#if Parameter.getName()??> <#assign Pname = Parameter.getName()/>
                                    <#else> <#assign Pname = "-"/>
                                    </#if>
                                    <#if Parameter.getIn()??> <#assign PParamType = Parameter.getIn()/>
                                    <#else> <#assign PParamType = "-"/>
                                    </#if>
                                    <#if Parameter.getDescription()??> <#assign PDescription = Parameter.getDescription()/>
                                    <#else> <#assign PDescription = "-"/>
                                    </#if>
                                    <#if Parameter.getRequired()??> <#assign PRequired = Parameter.getRequired()/>
                                    <#else> <#assign PRequired = "-"/>
                                    </#if>
                                    <#assign ff = 0/>
                                    <#if (Parameter.getType())??> <#assign PType = Parameter.getType()/>
                                    <#else> <#assign PType = "-"/>
                                    </#if>
                                    <#if (Parameter.getSchema())??>
                                        <#if (Parameter.getSchema().getType())??>
                                            <#assign PType = "array"/>
                                            <#assign ff = 1/>
                                        <#else>
                                            <#assign PType = Parameter.getSchema().get$ref()/>
                                        </#if>
                                    </#if>
                                    <td>${Pname}</td>
                                    <td>${PParamType}</td>
                                    <td>${PDescription}</td>
                                    <td><#if PRequired == true> Compulsory <#else> Optional </#if></td>
                                    <#if PType == "array">
                                        <#if ff = 1>
                                            <td>${"Array["}${Parameter.getSchema().getItems().get$ref()}${"]"}</td>
                                        <#else>
                                            <#if (Parameter.getItems().getType())??>
                                                <td>${"Array["}${Parameter.getItems().getType()}${"]"}</td>
                                            <#else>
                                                <td>${"Array["}${Parameter.getItems().get$ref()}${"]"}</td>
                                            </#if>
                                        </#if>
                                    <#else>
                                        <td>${PType}</td>
                                    </#if>
                                </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if get.getResponses()??>
                <tr>
                    <td>Responses</td>
                    <#assign responses = get.getResponses()/>
                    <td>
                        <#list responses?keys as responseCode>
                            <p> ${responseCode}, ${responses?values[responseCode_index].getDescription()}</p>
                            <#if responses?values[responseCode_index].getSchema()??> <#list models?keys as model>
                                <#if responses?values[responseCode_index].getSchema().getType() == "ref"><#if model == responses?values[responseCode_index].getSchema().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                               <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                                <#if responses?values[responseCode_index].getSchema().getType() == "array"><#if model == responses?values[responseCode_index].getSchema().getItems().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                            </#list>
                            </#if>
                        </#list>
                    </td>
                </tr>
            </#if>
        </table>
        </#if>
        <#if operation.getPut()??>
            <#assign put = operation.getPut()/>
        <table border = "1">
            <tr>
                <td> Method </td>
                <td> PUT </td>
            </tr>
            <#if put.getSummary()??>
                <tr>
                    <td>Summary</td>
                    <td>${put.getSummary()}</td>
                </tr>
            </#if>
            <#if put.getDescription()??>
                <tr>
                    <td>Description</td>
                    <td>${put.getDescription()}</td>
                </tr>
            </#if>
            <#if put.getOperationId()??>
                <tr>
                    <td>Operation Id</td>
                    <td>${put.getOperationId()}</td>
                </tr>
            </#if>
            <#if put.getProduces()??>
                <tr>
                    <td> Produces </td>
                    <td>
                        <table border = "1">
                            <#list put.getProduces() as produces>
                                <td> ${produces} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if put.getConsumes()??>
                <tr>
                    <td> Consumes </td>
                    <td>
                        <table border = "1">
                            <#list put.getConsumes() as consumes>
                                <td> ${consumes} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if put.getParameters()??>
                <tr>
                    <td> Parameters </td>
                    <td>
                        <table border = "1">
                            <tr>
                                <td> Name </td>
                                <td> Param Type </td>
                                <td> Description </td>
                                <td> Required </td>
                                <td> Type </td>
                            </tr>
                            <#list put.getParameters() as Parameter>
                                <tr>
                                    <#if Parameter.getName()??> <#assign Pname = Parameter.getName()/>
                                    <#else> <#assign Pname = "-"/>
                                    </#if>
                                    <#if Parameter.getIn()??> <#assign PParamType = Parameter.getIn()/>
                                    <#else> <#assign PParamType = "-"/>
                                    </#if>
                                    <#if Parameter.getDescription()??> <#assign PDescription = Parameter.getDescription()/>
                                    <#else> <#assign PDescription = "-"/>
                                    </#if>
                                    <#if Parameter.getRequired()??> <#assign PRequired = Parameter.getRequired()/>
                                    <#else> <#assign PRequired = "-"/>
                                    </#if>
                                    <#assign ff = 0/>
                                    <#if (Parameter.getType())??> <#assign PType = Parameter.getType()/>
                                    <#else> <#assign PType = "-"/>
                                    </#if>
                                    <#if (Parameter.getSchema())??>
                                        <#if (Parameter.getSchema().getType())??>
                                            <#assign PType = "array"/>
                                            <#assign ff = 1/>
                                        <#else>
                                            <#assign PType = Parameter.getSchema().get$ref()/>
                                        </#if>
                                    </#if>
                                    <td>${Pname}</td>
                                    <td>${PParamType}</td>
                                    <td>${PDescription}</td>
                                    <td><#if PRequired == true> Compulsory <#else> Optional </#if></td>
                                    <#if PType == "array">
                                        <#if ff = 1>
                                            <td>${"Array["}${Parameter.getSchema().getItems().get$ref()}${"]"}</td>
                                        <#else>
                                            <#if (Parameter.getItems().getType())??>
                                                <td>${"Array["}${Parameter.getItems().getType()}${"]"}</td>
                                            <#else>
                                                <td>${"Array["}${Parameter.getItems().get$ref()}${"]"}</td>
                                            </#if>
                                        </#if>
                                    <#else>
                                        <td>${PType}</td>
                                    </#if>
                                </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if put.getResponses()??>
                <tr>
                    <td>Responses</td>
                    <#assign responses = put.getResponses()/>
                    <td>
                        <#list responses?keys as responseCode>
                            <p> ${responseCode}, ${responses?values[responseCode_index].getDescription()}</p>
                            <#if responses?values[responseCode_index].getSchema()??> <#list models?keys as model>
                                <#if responses?values[responseCode_index].getSchema().getType() == "ref"><#if model == responses?values[responseCode_index].getSchema().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                                <#if responses?values[responseCode_index].getSchema().getType() == "array"><#if model == responses?values[responseCode_index].getSchema().getItems().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                            </#list>
                            </#if>
                        </#list>
                    </td>
                </tr>
            </#if>
        </table>
        </#if>
        <#if operation.getPost()??>
            <#assign post = operation.getPost()/>
        <table border = "1">
            <tr>
                <td> Method </td>
                <td> POST </td>
            </tr>
            <#if post.getSummary()??>
                <tr>
                    <td>Summary</td>
                    <td>${post.getSummary()}</td>
                </tr>
            </#if>
            <#if post.getDescription()??>
                <tr>
                    <td>Description</td>
                    <td>${post.getDescription()}</td>
                </tr>
            </#if>
            <#if post.getOperationId()??>
                <tr>
                    <td>Operation Id</td>
                    <td>${post.getOperationId()}</td>
                </tr>
            </#if>
            <#if post.getProduces()??>
                <tr>
                    <td> Produces </td>
                    <td>
                        <table border = "1">
                            <#list post.getProduces() as produces>
                                <td> ${produces} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if post.getConsumes()??>
                <tr>
                    <td> Consumes </td>
                    <td>
                        <table border = "1">
                            <#list post.getConsumes() as consumes>
                                <td> ${consumes} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if post.getParameters()??>
                <tr>
                    <td> Parameters </td>
                    <td>
                        <table border = "1">
                            <tr>
                                <td> Name </td>
                                <td> Param Type </td>
                                <td> Description </td>
                                <td> Required </td>
                                <td> Type </td>
                            </tr>
                            <#list post.getParameters() as Parameter>
                                <tr>
                                    <#if Parameter.getName()??> <#assign Pname = Parameter.getName()/>
                                    <#else> <#assign Pname = "-"/>
                                    </#if>
                                    <#if Parameter.getIn()??> <#assign PParamType = Parameter.getIn()/>
                                    <#else> <#assign PParamType = "-"/>
                                    </#if>
                                    <#if Parameter.getDescription()??> <#assign PDescription = Parameter.getDescription()/>
                                    <#else> <#assign PDescription = "-"/>
                                    </#if>
                                    <#if Parameter.getRequired()??> <#assign PRequired = Parameter.getRequired()/>
                                    <#else> <#assign PRequired = "-"/>
                                    </#if>
                                    <#assign ff = 0/>
                                    <#if (Parameter.getType())??> <#assign PType = Parameter.getType()/>
                                    <#else> <#assign PType = "-"/>
                                    </#if>
                                    <#if (Parameter.getSchema())??>
                                        <#if (Parameter.getSchema().getType())??>
                                            <#assign PType = "array"/>
                                            <#assign ff = 1/>
                                        <#else>
                                            <#assign PType = Parameter.getSchema().get$ref()/>
                                        </#if>
                                    </#if>
                                    <td>${Pname}</td>
                                    <td>${PParamType}</td>
                                    <td>${PDescription}</td>
                                    <td><#if PRequired == true> Compulsory <#else> Optional </#if></td>
                                    <#if PType == "array">
                                        <#if ff = 1>
                                            <td>${"Array["}${Parameter.getSchema().getItems().get$ref()}${"]"}</td>
                                        <#else>
                                            <#if (Parameter.getItems().getType())??>
                                                <td>${"Array["}${Parameter.getItems().getType()}${"]"}</td>
                                            <#else>
                                                <td>${"Array["}${Parameter.getItems().get$ref()}${"]"}</td>
                                            </#if>
                                        </#if>
                                    <#else>
                                        <td>${PType}</td>
                                    </#if>
                                </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if post.getResponses()??>
                <tr>
                    <td>Responses</td>
                    <#assign responses = post.getResponses()/>
                    <td>
                        <#list responses?keys as responseCode>
                            <p> ${responseCode}, ${responses?values[responseCode_index].getDescription()}</p>
                            <#if responses?values[responseCode_index].getSchema()??> <#list models?keys as model>
                                <#if responses?values[responseCode_index].getSchema().getType() == "ref"><#if model == responses?values[responseCode_index].getSchema().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                                <#if responses?values[responseCode_index].getSchema().getType() == "array"><#if model == responses?values[responseCode_index].getSchema().getItems().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                            </#list>
                            </#if>
                        </#list>
                    </td>
                </tr>
            </#if>
        </table>
        </#if>

        <#if operation.getDelete()??>
        <table border = "1">
            <#assign delete = operation.getDelete()/>
            <tr>
                <td> Method </td>
                <td> DELETE </td>
            </tr>
            <#if delete.getSummary()??>
                <tr>
                    <td>Summary</td>
                    <td>${delete.getSummary()}</td>
                </tr>
            </#if>
            <#if delete.getOperationId()??>
                <tr>
                    <td>Operation Id</td>
                    <td>${delete.getOperationId()}</td>
                </tr>
            </#if>
            <#if delete.getProduces()??>
                <tr>
                    <td> Produces </td>
                    <td>
                        <table border = "1">
                            <#list delete.getProduces() as produces>
                                <td> ${produces} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if delete.getConsumes()??>
                <tr>
                    <td> Consumes </td>
                    <td>
                        <#list delete.getConsumes() as consumes> ${consumes} </#list>
                    </td>
                </tr>
            </#if>
            <#if delete.getParameters()??>
                <tr>
                    <td> Parameters </td>
                    <td>
                        <table border = "1">
                            <tr>
                                <td> Name </td>
                                <td> Param Type </td>
                                <td> Description </td>
                                <td> Required </td>
                                <td> Type </td>
                            </tr>
                            <#list delete.getParameters() as Parameter>
                                <tr>
                                    <#if Parameter.getName()??> <#assign Pname = Parameter.getName()/>
                                    <#else> <#assign Pname = "-"/>
                                    </#if>
                                    <#if Parameter.getIn()??> <#assign PParamType = Parameter.getIn()/>
                                    <#else> <#assign PParamType = "-"/>
                                    </#if>
                                    <#if Parameter.getDescription()??> <#assign PDescription = Parameter.getDescription()/>
                                    <#else> <#assign PDescription = "-"/>
                                    </#if>
                                    <#if Parameter.getRequired()??> <#assign PRequired = Parameter.getRequired()/>
                                    <#else> <#assign PRequired = "-"/>
                                    </#if>
                                    <#assign ff = 0/>
                                    <#if (Parameter.getType())??> <#assign PType = Parameter.getType()/>
                                    <#else> <#assign PType = "-"/>
                                    </#if>
                                    <#if (Parameter.getSchema())??>
                                        <#if (Parameter.getSchema().getType())??>
                                            <#assign PType = "array"/>
                                            <#assign ff = 1/>
                                        <#else>
                                            <#assign PType = Parameter.getSchema().get$ref()/>
                                        </#if>
                                    </#if>
                                    <td>${Pname}</td>
                                    <td>${PParamType}</td>
                                    <td>${PDescription}</td>
                                    <td><#if PRequired == true> Compulsory <#else> Optional </#if></td>
                                    <#if PType == "array">
                                        <#if ff = 1>
                                            <td>${"Array["}${Parameter.getSchema().getItems().get$ref()}${"]"}</td>
                                        <#else>
                                            <#if (Parameter.getItems().getType())??>
                                                <td>${"Array["}${Parameter.getItems().getType()}${"]"}</td>
                                            <#else>
                                                <td>${"Array["}${Parameter.getItems().get$ref()}${"]"}</td>
                                            </#if>
                                        </#if>
                                    <#else>
                                        <td>${PType}</td>
                                    </#if>
                                </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if delete.getResponses()??>
                <tr>
                    <td>Responses</td>
                    <#assign responses = delete.getResponses()/>
                    <td>
                        <#list responses?keys as responseCode>
                            <p> ${responseCode}, ${responses?values[responseCode_index].getDescription()}</p>
                            <#if responses?values[responseCode_index].getSchema()??> <#list models?keys as model>
                                <#if responses?values[responseCode_index].getSchema().getType() == "ref"><#if model == responses?values[responseCode_index].getSchema().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                                <#if responses?values[responseCode_index].getSchema().getType() == "array"><#if model == responses?values[responseCode_index].getSchema().getItems().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                            </#list>
                            </#if>
                        </#list>
                    </td>
                </tr>
            </#if>
        </table>
        </#if>
        <#if operation.getPatch()??>
            <#assign patch = operation.getPatch()/>
        <table border = "1">
            <tr>
                <td> Method </td>
                <td> PATCH </td>
            </tr>
            <#if patch.getSummary()??>
                <tr>
                    <td>Summary</td>
                    <td>${patch.getSummary()}</td>
                </tr>
            </#if>
            <#if patch.getOperationId()??>
                <tr>
                    <td>Operation Id</td>
                    <td>${patch.getOperationId()}</td>
                </tr>
            </#if>
            <#if patch.getProduces()??>
                <tr>
                    <td> Produces </td>
                    <td>
                        <table border = "1">
                            <#list patch.getProduces() as produces>
                                <td> ${produces} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if patch.getConsumes()??>
                <tr>
                    <td> Consumes </td>
                    <td>
                        <#list patch.getConsumes() as consumes> ${consumes} </#list>
                    </td>
                </tr>
            </#if>
            <#if patch.getParameters()??>
                <tr>
                    <td> Parameters </td>
                    <td>
                        <table border = "1">
                            <tr>
                                <td> Name </td>
                                <td> Param Type </td>
                                <td> Description </td>
                                <td> Required </td>
                                <td> Type </td>
                            </tr>
                            <#list patch.getParameters() as Parameter>
                                <tr>
                                    <#if Parameter.getName()??> <#assign Pname = Parameter.getName()/>
                                    <#else> <#assign Pname = "-"/>
                                    </#if>
                                    <#if Parameter.getIn()??> <#assign PParamType = Parameter.getIn()/>
                                    <#else> <#assign PParamType = "-"/>
                                    </#if>
                                    <#if Parameter.getDescription()??> <#assign PDescription = Parameter.getDescription()/>
                                    <#else> <#assign PDescription = "-"/>
                                    </#if>
                                    <#if Parameter.getRequired()??> <#assign PRequired = Parameter.getRequired()/>
                                    <#else> <#assign PRequired = "-"/>
                                    </#if>
                                    <#assign ff = 0/>
                                    <#if (Parameter.getType())??> <#assign PType = Parameter.getType()/>
                                    <#else> <#assign PType = "-"/>
                                    </#if>
                                    <#if (Parameter.getSchema())??>
                                        <#if (Parameter.getSchema().getType())??>
                                            <#assign PType = "array"/>
                                            <#assign ff = 1/>
                                        <#else>
                                            <#assign PType = Parameter.getSchema().get$ref()/>
                                        </#if>
                                    </#if>
                                    <td>${Pname}</td>
                                    <td>${PParamType}</td>
                                    <td>${PDescription}</td>
                                    <td><#if PRequired == true> Compulsory <#else> Optional </#if></td>
                                    <#if PType == "array">
                                        <#if ff = 1>
                                            <td>${"Array["}${Parameter.getSchema().getItems().get$ref()}${"]"}</td>
                                        <#else>
                                            <#if (Parameter.getItems().getType())??>
                                                <td>${"Array["}${Parameter.getItems().getType()}${"]"}</td>
                                            <#else>
                                                <td>${"Array["}${Parameter.getItems().get$ref()}${"]"}</td>
                                            </#if>
                                        </#if>
                                    <#else>
                                        <td>${PType}</td>
                                    </#if>
                                </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if patch.getResponses()??>
                <tr>
                    <td>Responses</td>
                    <#assign responses = patch.getResponses()/>
                    <td>
                        <#list responses?keys as responseCode>
                            <p> ${responseCode}, ${responses?values[responseCode_index].getDescription()}</p>
                            <#if responses?values[responseCode_index].getSchema()??> <#list models?keys as model>
                                <#if responses?values[responseCode_index].getSchema().getType() == "ref"><#if model == responses?values[responseCode_index].getSchema().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                                <#if responses?values[responseCode_index].getSchema().getType() == "array"><#if model == responses?values[responseCode_index].getSchema().getItems().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                            </#list>
                            </#if>
                        </#list>
                    </td>
                </tr>
            </#if>
        </table>
        </#if>
        <#if operation.getOptions()??>
            <#assign options = operation.getOptions()/>
        <table border = "1">
            <tr>
                <td> Method </td>
                <td> OPTIONS </td>
            </tr>
            <#if options.getSummary()??>
                <tr>
                    <td>Summary</td>
                    <td>${options.getSummary()}</td>
                </tr>
            </#if>
            <#if options.getOperationId()??>
                <tr>
                    <td>Operation Id</td>
                    <td>${options.getOperationId()}</td>
                </tr>
            </#if>
            <#if options.getProduces()??>
                <tr>
                    <td> Produces </td>
                    <td>
                        <table border = "1">
                            <#list options.getProduces() as produces>
                                <td> ${produces} </td>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if options.getConsumes()??>
                <tr>
                    <td> Consumes </td>
                    <td>
                        <#list options.getConsumes() as consumes> ${consumes} </#list>
                    </td>
                </tr>
            </#if>
            <#if options.getParameters()??>
                <tr>
                    <td> Parameters </td>
                    <td>
                        <table border = "1">
                            <tr>
                                <td> Name </td>
                                <td> Param Type </td>
                                <td> Description </td>
                                <td> Required </td>
                                <td> Type </td>
                            </tr>
                            <#list options.getParameters() as Parameter>
                                <tr>
                                    <#if Parameter.getName()??> <#assign Pname = Parameter.getName()/>
                                    <#else> <#assign Pname = "-"/>
                                    </#if>
                                    <#if Parameter.getIn()??> <#assign PParamType = Parameter.getIn()/>
                                    <#else> <#assign PParamType = "-"/>
                                    </#if>
                                    <#if Parameter.getDescription()??> <#assign PDescription = Parameter.getDescription()/>
                                    <#else> <#assign PDescription = "-"/>
                                    </#if>
                                    <#if Parameter.getRequired()??> <#assign PRequired = Parameter.getRequired()/>
                                    <#else> <#assign PRequired = "-"/>
                                    </#if>
                                    <#assign ff = 0/>
                                    <#if (Parameter.getType())??> <#assign PType = Parameter.getType()/>
                                    <#else> <#assign PType = "-"/>
                                    </#if>
                                    <#if (Parameter.getSchema())??>
                                        <#if (Parameter.getSchema().getType())??>
                                            <#assign PType = "array"/>
                                            <#assign ff = 1/>
                                        <#else>
                                            <#assign PType = Parameter.getSchema().get$ref()/>
                                        </#if>
                                    </#if>
                                    <td>${Pname}</td>
                                    <td>${PParamType}</td>
                                    <td>${PDescription}</td>
                                    <td><#if PRequired == true> Compulsory <#else> Optional </#if></td>
                                    <#if PType == "array">
                                        <#if ff = 1>
                                            <td>${"Array["}${Parameter.getSchema().getItems().get$ref()}${"]"}</td>
                                        <#else>
                                            <#if (Parameter.getItems().getType())??>
                                                <td>${"Array["}${Parameter.getItems().getType()}${"]"}</td>
                                            <#else>
                                                <td>${"Array["}${Parameter.getItems().get$ref()}${"]"}</td>
                                            </#if>
                                        </#if>
                                    <#else>
                                        <td>${PType}</td>
                                    </#if>
                                </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </#if>
            <#if options.getResponses()??>
                <tr>
                    <td>Responses</td>
                    <#assign responses = options.getResponses()/>
                    <td>
                        <#list responses?keys as responseCode>
                            <p> ${responseCode}, ${responses?values[responseCode_index].getDescription()}</p>
                            <#if responses?values[responseCode_index].getSchema()??> <#list models?keys as model>
                                <#if responses?values[responseCode_index].getSchema().getType() == "ref"><#if model == responses?values[responseCode_index].getSchema().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                                <#if responses?values[responseCode_index].getSchema().getType() == "array"><#if model == responses?values[responseCode_index].getSchema().getItems().get$ref()>
                                    <table border = "1">
                                        <tr>
                                            <td>
                                                <code> ${models[model]}]] </code>
                                            </td>
                                            <td>
                                                <code> ${schema[model]}]] </code>
                                            </td>
                                        </tr>
                                    </table>
                                </#if> </#if>
                            </#list>
                            </#if>
                        </#list>
                    </td>
                </tr>
            </#if>
        </table>
        </#if>
    </#list>
</#if>

<b>                                                                 This is automatic generated documentation. No editing allowed          </b>
<?xml version="1.0" encoding="UTF-8"?>
<!--

    Copyright 2021 - TOOP Project

    This file and its contents are licensed under the EUPL, Version 1.2
    or – as soon they will be approved by the European Commission – subsequent
    versions of the EUPL (the "Licence");

    You may not use this work except in compliance with the Licence.
    You may obtain a copy of the Licence at:

          https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12

    Unless required by applicable law or agreed to in writing, software distributed under the Licence is distributed on an "AS IS" basis,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

    See the Licence for the specific language governing permissions and limitations under the Licence.

-->
<schema xmlns="http://purl.oclc.org/dsdl/schematron" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    queryBinding="xslt2" 
    >
    <ns prefix="query"  uri="urn:oasis:names:tc:ebxml-regrep:xsd:query:4.0"/>
 
    
    <title>TOOP EDM - isRequest Checker</title>
    
    
    <pattern>
        <rule context="/">
            <assert test="( (exists(query:QueryRequest)) )"  flag='ERROR' id="NOT_A_REQUEST">
                The message does not look like a QueryRequest. 
            </assert>
        </rule>
    </pattern>
    
   
</schema>
<?xml version="1.0" encoding="UTF-8"?>
<!--

    This work is protected under copyrights held by the members of the
    TOOP Project Consortium as indicated at
    http://wiki.ds.unipi.gr/display/TOOP/Contributors
    (c) 2018-2021. All rights reserved.

    This work is licensed under the EUPL 1.2.

     = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    Licensed under the EUPL, Version 1.2 or – as soon they will be approved
    by the European Commission - subsequent versions of the EUPL
    (the "Licence");
    You may not use this work except in compliance with the Licence.
    You may obtain a copy of the Licence at:

            https://joinup.ec.europa.eu/software/page/eupl

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
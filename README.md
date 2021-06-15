# TOOP Commons NG

The code contained in this project is used by:
* https://github.com/TOOP4EU/data-services-directory - Data Services Directory (DSD)
* https://github.com/TOOP4EU/toop-connector-ng - TOOP Connector NG
* https://github.com/TOOP4EU/toop-simulator-ng/ - TOOP Simulator NG
* https://github.com/TOOP4EU/toop-playground-ng/ - Playground NG

#  License

All rights to the results that are made available via this repository are owned by their respective creators, as identified in the relevant file names. Unless explicitly indicated otherwise, the results are made available to you under the EUPL, Version 1.2, an EU approved open source licence. For a full version of the licence and guidance, please visit https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12

Note that the results are protected by copyright, and all rights which are not expressly licenced to you by the owners or granted by applicable law are explicitly reserved.

This repository is the only formal source of the results of the TOOP project, an action that was funded by the EU Horizon 2020 research and innovation programme under grant agreement No 737460 (see https://toop.eu/). If you have obtained the results elsewhere or under a different licence, it is likely that this is in violation of copyright law. In case of doubt, please contact us.  

## Building

Requires at least

* Java 1.8 or later
* Apache Maven for building

Do an initial `mvn clean install` on the command line.

Afterwards don't forget to add the following paths to your build path (in your IDE):

* toop-regrep/target/generated-sources/xjc
* toop-edm/target/generated-sources/xjc

Note: the `toop-codelist-tools` is for internal usage only.


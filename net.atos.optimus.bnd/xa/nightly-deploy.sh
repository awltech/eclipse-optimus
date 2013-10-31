# Script used as post build action on jenkins/hudson, to collect all update sites into one temp folder
# and aggregate them into one update site, using B3 aggregation. Note that this configuration
# is environment-dependant and it needs to be duplicated/updated when the CI environment changes.
#
# Author: mvanbesien

echo "**************************************************"
echo "* Optimus Update Site Aggregation and Deployment *"
echo "**************************************************"
TEMPDIR=/DATA/xa/temp/optimus

echo "* Isolating individual update sites into temporary structure (1/4)..."
rm -rf ${TEMPDIR}/*
mkdir ${TEMPDIR}/common
mkdir ${TEMPDIR}/m2m-engine
mkdir ${TEMPDIR}/m2m-engine-sdk
mkdir ${TEMPDIR}/m2m-javaxmi
mkdir ${TEMPDIR}/m2t-java
mkdir ${TEMPDIR}/m2t-merger-java
mkdir ${TEMPDIR}/m2t-selector
mkdir ${TEMPDIR}/t2m-java

cp -a "${WORKSPACE}/net.atos.optimus.common.parent/net.atos.optimus.common.site/target/repository/." ${TEMPDIR}/common
cp -a "${WORKSPACE}/net.atos.optimus.m2m.engine.parent/net.atos.optimus.m2m.engine.site/target/repository/." ${TEMPDIR}/m2m-engine
cp -a "${WORKSPACE}/net.atos.optimus.m2m.engine.sdk.parent/net.atos.optimus.m2m.engine.sdk.site/target/repository/." ${TEMPDIR}/m2m-engine-sdk
cp -a "${WORKSPACE}/net.atos.optimus.m2m.javaxmi.parent/net.atos.optimus.m2m.javaxmi.site/target/repository/." ${TEMPDIR}/m2m-javaxmi
cp -a "${WORKSPACE}/net.atos.optimus.m2t.java.parent/net.atos.optimus.m2t.java.site/target/repository/." ${TEMPDIR}/m2t-java
cp -a "${WORKSPACE}/net.atos.optimus.m2t.merger.java.parent/net.atos.optimus.m2t.merger.java.site/target/repository/." ${TEMPDIR}/m2t-merger-java
cp -a "${WORKSPACE}/net.atos.optimus.m2t.selector.parent/net.atos.optimus.m2t.selector.site/target/repository/." ${TEMPDIR}/m2t-selector
cp -a "${WORKSPACE}/net.atos.optimus.t2m.java.parent/net.atos.optimus.t2m.java.site/target/repository/." ${TEMPDIR}/t2m-java

echo "* Invoking B3 aggregation process (2/4)..."
/DATA/xa/CI/eclipseHeadlessB3/b3 aggregate --action CLEAN_BUILD --buildModel "${WORKSPACE}/net.atos.optimus.build.b3/xa/nightly-aggregation.b3aggr" --buildRoot ${TEMPDIR}/b3 --logLevel WARNING --eclipseLogLevel WARNING

echo "* Deploying aggregated update site (3/4)..."
rm -rf /DATA/xa/indivXAPluginsUpdSite/net.atos.optimus/aggregated/*
cp -a ${TEMPDIR}/b3/final/. /DATA/xa/indivXAPluginsUpdSite/net.atos.optimus/aggregated/
echo "Done at /DATA/xa/indivXAPluginsUpdSite/net.atos.optimus/aggregated/"
rm -rf /DATA/xa/eclipse/nightly/optimus/*
cp -a ${TEMPDIR}/b3/final/. /DATA/xa/eclipse/nightly/optimus/
echo "Done at /DATA/xa/eclipse/nightly/optimus/"

echo "* Cleaning temporary structure (4/4)..."
rm -rf ${TEMPDIR}/*

echo "* Aggregation and deployment completed successfully !"
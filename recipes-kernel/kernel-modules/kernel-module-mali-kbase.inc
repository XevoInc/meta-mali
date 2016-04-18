inherit module

SUMMARY = "Open Source Mali Midgard GPU Kernel Drivers"
SRC_URI = "http://malideveloper.arm.com/downloads/drivers/TX011/${PV}-${PR}/${MALI_PACKAGE_NAME}.tgz;name=driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://driver/product/kernel/license.txt;md5=13e14ae1bd7ad5bff731bba4a31bb510"

EXTRA_OEMAKE = ""

S = "${WORKDIR}/${MALI_PACKAGE_NAME}/"

do_compile() {
	oe_runmake -C ${STAGING_KERNEL_DIR} \
		M=$(pwd)/driver/product/kernel/drivers/gpu/arm/midgard \
		O=${STAGING_KERNEL_BUILDDIR} \
		EXTRA_CFLAGS="${MALI_FLAGS}" \
		${MALI_KCONFIG}
}

do_install() {
	oe_runmake -C ${STAGING_KERNEL_DIR} \
		M=$(pwd)/driver/product/kernel/drivers/gpu/arm/midgard \
		INSTALL_MOD_PATH="${D}" \
		O=${STAGING_KERNEL_BUILDDIR} \
		EXTRA_CFLAGS="${MALI_FLAGS}" \
		${MALI_KCONFIG} \
		modules_install
}

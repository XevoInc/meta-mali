include include/u-boot-mali.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

PROVIDES += "u-boot"
DEPENDS += "dtc-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "(odroidxu3)"

SRC_URI += "file://boot-linux.cmd \
	    https://github.com/hardkernel/u-boot/blob/odroidxu3-v2012.07/sd_fuse/hardkernel_1mb_uboot/bl1.bin.hardkernel?raw=true;name=bl1;downloadfilename=bl1.bin.hardkernel \
	    https://github.com/hardkernel/u-boot/blob/odroidxu3-v2012.07/sd_fuse/hardkernel_1mb_uboot/bl2.bin.hardkernel.1mb_uboot?raw=true;name=bl2;downloadfilename=bl2.bin.hardkernel.1mb_uboot \
	    https://github.com/hardkernel/u-boot/blob/odroidxu3-v2012.07/sd_fuse/hardkernel_1mb_uboot/tzsw.bin.hardkernel?raw=true;name=tzsw;downloadfilename=tzsw.bin.hardkernel \
"
SRC_URI[bl1.md5sum] = "3908379f82f972ece88ca1b5a280b5fd"
SRC_URI[bl1.sha256sum] = "7b940288acfcabd9388d59b785d1527b2d5ff9aaa36e63f970e054a9b695be31"
SRC_URI[bl2.md5sum] = "09c42bed980921cfc914e97e067ba9a3"
SRC_URI[bl2.sha256sum] = "255afd9a4b4684c6e19a09f703d91e642ac5fd2dfb90192c2a3e23202974d373"
SRC_URI[tzsw.md5sum] = "fd01dda20b999e0b731c7063431a42b3"
SRC_URI[tzsw.sha256sum] = "dacbe8e367c13272477066f84f81d20fe47078154007ed1f2773722354639810"

SRCREV = "fa85e826c16b9ce1ad302a57e9c4b24db0d8b930"
EXTRA_OEMAKE_append = " KCFLAGS=-fgnu89-inline"

UBOOT_MACHINE = "odroid-xu3_config"
UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "u-boot-dtb.${UBOOT_SUFFIX}"

do_deploy () {
	install -d ${DEPLOYDIR}
	install ${S}/${UBOOT_BINARY} ${DEPLOYDIR}/${UBOOT_IMAGE}

	cd ${DEPLOYDIR}
	rm -f ${UBOOT_BINARY} ${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${UBOOT_BINARY}

	${S}/tools/mkimage -T script -C none -d ${S}/../boot-linux.cmd ${S}/boot.scr

	install ${S}/../bl1.bin.hardkernel ${DEPLOYDIR}/bl1.bin.hardkernel
	install ${S}/../bl2.bin.hardkernel.1mb_uboot ${DEPLOYDIR}/bl2.bin.hardkernel
	install ${S}/../tzsw.bin.hardkernel ${DEPLOYDIR}/tzsw.bin.hardkernel
	install ${S}/boot.scr ${DEPLOYDIR}/boot.scr
}

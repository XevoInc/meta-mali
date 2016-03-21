inherit module

SUMMARY = "Open Source Mali Midgard GPU Kernel Drivers"
SRC_URI = "http://malideveloper.arm.com/downloads/drivers/TX011/${PV}-${PR}/${MALI_PACKAGE_NAME}.tgz;name=driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://driver/product/kernel/license.txt;md5=13e14ae1bd7ad5bff731bba4a31bb510"

EXTRA_OEMAKE = ""

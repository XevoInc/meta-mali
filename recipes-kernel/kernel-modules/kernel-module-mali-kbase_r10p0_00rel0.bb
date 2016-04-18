require kernel-module-mali-kbase.inc

inherit module

MALI_PACKAGE_NAME = "TX011-SW-99002-r10p0-00rel0"

SRC_URI[driver.md5sum] = "325d0cf7500e63f4a2cc993aa69585a9"
SRC_URI[driver.sha256sum] = "887363f6dea2071e698d54f1c6d5a2ebba9a552960b525751e6e5453ab2f4672"

5422_PATCH = "file://mali-5422-platform-r10p0.patch"

python() {
	platform = d.getVar('MALI_DRIVER_PLATFORM', True)
	if not platform:
		platform = "devicetree"

	config = ["CONFIG_MALI_MIDGARD=m",
		  "CONFIG_MALI_GATOR_SUPPORT=y",
		  "CONFIG_MALI_MIDGARD_DVFS=y",
		  "CONFIG_MALI_EXPERT=y",
		  "CONFIG_MALI_PLATFORM_FAKE=y",
		  "CONFIG_MALI_PLATFORM_THIRDPARTY=y",
		  "CONFIG_MALI_PLATFORM_THIRDPARTY_NAME=" + platform]

	if platform == "5422":
		d.appendVar('SRC_URI', ' ' + d.getVar('5422_PATCH', True))

	for c in config:
		d.appendVar('MALI_FLAGS', '-D' + c + ' ')
		d.appendVar('MALI_KCONFIG', c + ' ')
}

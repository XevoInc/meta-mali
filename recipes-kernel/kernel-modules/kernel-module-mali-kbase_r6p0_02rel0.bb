require kernel-module-mali-kbase.inc

inherit module

MALI_PACKAGE_NAME = "TX011-SW-99002-r6p0-02rel0"

SRC_URI[driver.md5sum] = "6785d3d975f208e8c2ac38d3d0bf1ba1"
SRC_URI[driver.sha256sum] = "a05a82732e8c2bd3bbd3e9ff3967308f67010add210bb21cdc821fb1f38b2eb6"

5422_PATCH = "file://mali-5422-platform-r6p0.patch"

python() {
	platform = d.getVar('MALI_DRIVER_PLATFORM', True)
	if not platform:
		platform = "devicetree"

	config = ["CONFIG_MALI_MIDGARD=m",
		  "CONFIG_MALI_GATOR_SUPPORT=y",
		  "CONFIG_MALI_MIDGARD_DVFS=y",
		  "CONFIG_MALI_MIDGARD_RT_PM=y",
		  "CONFIG_MALI_MIDGARD_DEBUG_SYS=y",
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
#coding: utf-8
import sys
import os.path

import trans
import visual_studio_srm
import subprocess

#todo browserの設定を外部で可能にしたい
browser = r"C:\Users\math\AppData\Local\Google\Chrome\Application\chrome.exe";
try:
	code_name = sys.argv[1]
	#code_name = r"C:\math\Projects\competition\topcoder\BlurredDartboard.cpp"
	code_name = os.path.abspath(code_name)
	html_name = u"%s.html" % os.path.splitext(code_name)[0]


	visual_studio_srm.cmd_for_srm(code_name)
	out_html_name = trans.trans(html_name)

	subprocess.call([browser,out_html_name])
except Exception, e:
	print e
	
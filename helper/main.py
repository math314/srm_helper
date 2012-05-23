#coding: utf-8
import sys
import os.path
from subprocess import *

import trans
import visual_studio_srm

#todo browserの設定を外部で可能にしたい
browser = os.getenv('LOCALAPPDATA') + r"\Google\Chrome\Application\chrome.exe";
try:
	code_name = sys.argv[1]
	code_name = os.path.abspath(code_name)
	html_name = u"%s.html" % os.path.splitext(code_name)[0]


	visual_studio_srm.cmd_for_srm(code_name)
	out_html_name = trans.trans(html_name)

	#ブラウザでhtmlを開く
	Popen([browser,out_html_name])
except Exception, e:
	print e
	

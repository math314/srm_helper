import MultipartPostHandler, urllib2, cookielib
import sys
import os.path

def trans(filename):

    output_filename = u"%s.out%s" %(os.path.splitext(filename))
    
    cookies = cookielib.CookieJar()
    cookieProcessor = urllib2.HTTPCookieProcessor(cookies)
    opener = urllib2.build_opener(cookieProcessor)
    opener.addheaders = [
                         ('User-agent', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.53.11 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10'),
                         ('Origin','http://translate.google.co.jp'),
                         ('Referer','http://translate.google.co.jp/')]
    f = opener.open("http://translate.google.co.jp/")
    f.read()
    
    opener = urllib2.build_opener(cookieProcessor,MultipartPostHandler.MultipartPostHandler)
    opener.addheaders = [
                         ('User-agent', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.53.11 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10'),
                         ('Origin','http://translate.google.co.jp'),
                         ('Referer','http://translate.google.co.jp/')]
    
    
    params = {}
    params["sl"] = "en"
    params["tl"] = "ja"
    params["js"] = "n"
    params["prev"] = "_t"
    params["hl"] = "ja"
    params["ie"] = "UTF-8"
    params["eotf"] = "2"
    params["text"] = ""
    params["file"] = open(filename, "rb")
    f = opener.open("http://translate.googleusercontent.com/translate_f", params)
    
    out = open(output_filename,"wb")
    out.write(f.read())
    out.close()
    
    return output_filename

if __name__ == '__main__':
    filename = sys.argv[1]
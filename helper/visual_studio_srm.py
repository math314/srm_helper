#coding: utf-8
'''
Created on 2012/05/15

@author: math
'''
import visual_studio_hidemaru
import sys,os

def cmd_for_srm(abs_filename):
    """
    ファイルをvisualStudioの第一プロジェクトに追加する。
    その際、他の既存のcppファイルはプロジェクトから除外される。
  abs_filename-   ファイル名の絶対パス
                (Ex.) c:/project/my_app/src/main.cpp
    """
    
    dte = visual_studio_hidemaru.get_dte_obj(0)
    if not dte:
        visual_studio_hidemaru._vs_msg("Not found process")
        return False
   
    #一つ目のプロジェクトを選択する
    proj = dte.Solution.Projects[0]
    
    for item in visual_studio_hidemaru.iter_project_items(proj.ProjectItems):
        try:
            #プロジェクトアイテムのパスを取得する
            abs_fn = visual_studio_hidemaru._to_unicode(item.FileNames(1))
            
            print abs_fn
            
            #cppで終わっている場合
            if abs_fn.lower().endswith(".cpp"):
                #ファイルをプロジェクトから除外する
                item.Remove()
                visual_studio_hidemaru._vs_msg(abs_fn + " removed.")
                break
        except:
            pass
    #プロジェクトに、ファイルを追加する
    proj.ProjectItems.AddFromFile(abs_filename)
    
    visual_studio_hidemaru._vs_msg("Add Complete!!!")


if __name__ == '__main__':
    try:
        cmd_for_srm(argv[1])
    except Exception, ex:
        print "raise exception."
        print ex
    pass
package com.base.baselib.bean;

public class TaskNumber {

//
//    public int random_task;// 	int 	双随机任务数
//    public int recheck_task;// 	int 	复检任务数
//    public int report_task;// 	int 	投诉任务数
//    public int daily_task;// 	int 	日常任务数


    public int un_message;// 	int 	未读消息数
    public int task;// 	int 	待办任务数
    public int check_task;// 	int 	复检任务数
    public int complaint_task;// 	int 	投诉任务数



    public String unmessages ;//	html 	未读消息
    public String daiexamine;// 	html 	待审核
    public String weiexamine;// 	html 	审核未通过
    public String yiexamine;// 	html 	已完成


    @Override
    public String toString() {
        return "TaskNumber{" +
                "un_message=" + un_message +
                ", task=" + task +
                ", check_task=" + check_task +
                ", complaint_task=" + complaint_task +
                ", unmessages='" + unmessages + '\'' +
                ", daiexamine='" + daiexamine + '\'' +
                ", weiexamine='" + weiexamine + '\'' +
                ", yiexamine='" + yiexamine + '\'' +
                '}';
    }
}

class Task{

 private Integer startTimeHour;
 private Integer startTimeMinute;
 private String personName;
 private String reminderDescription;
 private Boolean dismiss;

 public Task(){
 }

 public Task(Integer startTimeHour, Integer startTimeMinute, String personName, String reminderDescription){
  this.startTimeHour = startTimeHour;
  this.startTimeMinute = startTimeMinute;
  this.personName = personName;
  this.reminderDescription = reminderDescription;
 }

 public Integer getStartTimeHour(){
  return this.startTimeHour;
 }

 public Integer getStartTimeMinute(){
  return this.startTimeMinute;
 }

 public String getPersonName(){
  return this.personName;
 }

 public String getReminderDescription(){
  return this.reminderDescription;
 }

 public void setDismiss(Boolean dismiss){
  this.dismiss = dismiss;
 }

 public Boolean getDismiss(){
  return this.dismiss;
 }
 
}
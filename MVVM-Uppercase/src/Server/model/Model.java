package Server.model;

import Server.utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  String convert(String source) throws Exception;
  void addLog(String log);
}

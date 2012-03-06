require "erb"

if __FILE__ == $PROGRAM_NAME
  count = 50
  uid = 6
  
  File.open('insert_messages.sql', 'w') do |file|
    for i in 1..count
      file.puts "INSERT INTO message(uid, content, create_time) VALUES(#{uid}, 'this is my message test at #{i}', '#{Time.now.strftime('%Y-%m-%d %H:%M:%S')}');"
    end
  end
end
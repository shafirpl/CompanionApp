//
//  NotesMainViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-08.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire

class NotesMainViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
        let urlString = "http://138.68.61.175:5500/notes"
    
    // for the realod button, I did it the same way
    // drag and drop method just like any other button
    @IBOutlet weak var reloadButton: UIBarButtonItem!
    
    @IBOutlet weak var notesTableView: UITableView!
    var notes = [noteItemStruct]()
    /*
     * The problem with viewDidLoad is that it will only runs once, so our
     * call to our backend will only happen once, we don't want that.
     * We want to run the get request every time we come to this screen
     * viewDidAppear fixes that, as it runs every time the screen is loaded
     */
    override func viewDidAppear(_ animated: Bool) {
        downloadJSON {
            self.notesTableView.reloadData()
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()


        downloadJSON {
            //print("Successful")
            /*
             * after downloading the data, this function will get executed, which in turn will
             * excute the two functions that we wrote below, the notes.count function and
             * initializing the row with title
             */
            self.notesTableView.reloadData()
        }
        
        notesTableView.delegate = self
        notesTableView.dataSource = self
    }
    // this basically tells us how many rows will be there
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return notes.count
    }
    // this basically initalizes every row with the title coming from our json array's objects
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: nil)
        cell.textLabel?.text = notes[indexPath.row].title.capitalized
        return cell
    }
    
    // now we need to pass the data to our screen where we have the stuff
    // in order to do so, we need to know which row was selected and
    // grab data from that cell and pass it
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // the identifier is the one we defined when we connected
        // the screen to here
        // screenshot in the google doc
        
        // this will tell the app to go to the note details screen,
        // this won't pass the data
        performSegue(withIdentifier: "showDetails", sender: self)
        
    }
    
    // this function passes data to the details view controller
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? NoteDetailsViewController{
            destination.noteItem = notes[(notesTableView.indexPathForSelectedRow?.row)!]
        }
    }
    
    /*
     * swipe to delete stuff
     * I used this article to read through them
     * https://www.hackingwithswift.com/example-code/uikit/how-to-swipe-to-delete-uitableviewcells
     * it is ridiculously easy, I didn't have to add anything visually. Just these lines of code
     * and suddenly I have a delete button. Just wow. In android I had to do a lot of weird stuff
     */
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if (editingStyle == .delete){
            /*
             * Now we need to do the last 2 lines in order
             * we don't need to reload the table data, as we will be deleting the data from our
             * array manually in order for it to work
             * first we need to make the backend call, as after deleting the stuff manually the
             * record at indexPath wont' exist, so we need to grab the record before deleting it
             * then we need to delete the entry from our array manually
             * after only then we can delete the row and do the animation
             */
            deleteNote(noteId: self.notes[indexPath.row].noteId)
            notes.remove(at: indexPath.row)
            notesTableView.deleteRows(at: [indexPath], with: .fade)
            
            
            
        }
    }
    
    func deleteNote(noteId:String){
        // print(noteId)
        let url = urlString + "/" + noteId
        AF.request(url,method: .delete).responseJSON{
            response in print(response)
        }
    }
    
    /*
     * basically with the closure completed function, what will happen is that
     * we will pass a function when we are calling this function as a parameter, and
     * it will execute this function after the download is done
     * so we could do downloadJSON{print("Something")}, and after
     * the download is complete, it will print "something" on the console
     */
    func downloadJSON(completed: @escaping () -> ()){
        let url = URL(string: urlString)
        URLSession.shared.dataTask(with: url!) { (data, response, error) in
            if error == nil{
                do{
                    self.notes = try JSONDecoder().decode([noteItemStruct].self, from: data!)
                    DispatchQueue.main.async {
                        completed()
                    }
                    
                } catch{
                    print("Json error")
                }
                
            }
        }.resume()
    }
    
    @IBAction func reloadButtonAction(_ sender: Any) {
        downloadJSON {
            self.notesTableView.reloadData()
        }
    }
    
}

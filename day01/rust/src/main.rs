use std::{env, process};

fn main() {
    let mut args = env::Args;
    args.next();

    let mynum = match args.next() {
        Some(arg) => arg,
        None => return Err("Didn't get a number"),
    };

    println!("Hello, world! {:?}", mynum);
}

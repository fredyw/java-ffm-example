use std::ffi::c_char;

#[repr(C)]
pub struct Input {
    a: i32,
    b: i32,
    op: c_char,
}

#[no_mangle]
pub extern "C" fn calculate(input: Input) -> i32 {
    let op = input.op as u8;
    match op {
        b'+' => input.a + input.b,
        b'-' => input.a - input.b,
        b'*' => input.a * input.b,
        _ => input.a / input.b,
    }
}

#[no_mangle]
pub extern "C" fn calculate1(a: i32, b: i32, op: c_char) -> i32 {
    let op = op as u8;
    match op {
        b'+' => a + b,
        b'-' => a - b,
        b'*' => a * b,
        _ => a / b,
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn add() {
        let result = calculate(Input {a: 1, b: 2, op: b'+' as c_char});
        assert_eq!(result, 3);
    }

    #[test]
    fn subtract() {
        let result = calculate(Input {a: 4, b: 2, op: b'-' as c_char});
        assert_eq!(result, 2);
    }

    #[test]
    fn multiply() {
        let result = calculate(Input {a: 1, b: 2, op: b'*' as c_char});
        assert_eq!(result, 2);
    }

    #[test]
    fn divide() {
        let result = calculate(Input {a: 4, b: 2, op: b'/' as c_char});
        assert_eq!(result, 2);
    }
}

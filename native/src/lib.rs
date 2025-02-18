#[repr(C)]
pub struct Input {
    a: i32,
    b: i32,
    op: char,
}

impl Input {
    fn new(a: i32, b: i32, op: char) -> Input {
        Self {a, b, op}
    }
}

#[no_mangle]
pub fn calculate(input: Input) -> i32 {
    match input.op {
        '+' => input.a + input.b,
        '-' => input.a - input.b,
        '*' => input.a * input.b,
        _ => input.a / input.b,
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn add() {
        let result = calculate(Input::new(1, 2, '+'));
        assert_eq!(result, 3);
    }

    #[test]
    fn subtract() {
        let result = calculate(Input::new(4, 2, '-'));
        assert_eq!(result, 2);
    }

    #[test]
    fn multiply() {
        let result = calculate(Input::new(1, 2, '*'));
        assert_eq!(result, 2);
    }

    #[test]
    fn divide() {
        let result = calculate(Input::new(4, 2, '/'));
        assert_eq!(result, 2);
    }
}

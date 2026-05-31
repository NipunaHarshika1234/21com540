.data
    prompt:        .asciiz "Enter a 3-digit integer: "
    arm_msg:       .asciiz "Armstrong\n"
    not_arm_msg:   .asciiz "Not Armstrong\n"

.text
.globl main
main:
    # 1. Input step
    la $a0, prompt
    jal prompt_read_int
    move $s0, $v0        # $s0 = Original Number

    # 2. Extract and Cube Digit 1 (Units Place - Position 0)
    move $a0, $s0
    li $a1, 0
    jal extract_digit
    move $t0, $v0        # $t0 = d1
    mul $t1, $t0, $t0
    mul $t1, $t1, $t0    # $t1 = d1^3

    # 3. Extract and Cube Digit 2 (Tens Place - Position 1)
    move $a0, $s0
    li $a1, 1
    jal extract_digit
    move $t0, $v0        # $t0 = d2
    mul $t2, $t0, $t0
    mul $t2, $t2, $t0    # $t2 = d2^3

    # 4. Extract and Cube Digit 3 (Hundreds Place - Position 2)
    move $a0, $s0
    li $a1, 2
    jal extract_digit
    move $t0, $v0        # $t0 = d3
    mul $t3, $t0, $t0
    mul $t3, $t3, $t0    # $t3 = d3^3

    # 5. Sum the cubes up
    add $s1, $t1, $t2    # Sum = d1^3 + d2^3
    add $s1, $s1, $t3    # Sum = d1^3 + d2^3 + d3^3

    # 6. Verify result
    beq $s0, $s1, is_armstrong

    la $a0, not_arm_msg
    jal print_string
    j end_q3

is_armstrong:
    la $a0, arm_msg
    jal print_string

end_q3:
    jal exit_program

# Include utility blocks
.include "Q1_util.asm"